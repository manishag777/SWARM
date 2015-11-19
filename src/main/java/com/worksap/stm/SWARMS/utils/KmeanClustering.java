package com.worksap.stm.SWARMS.utils;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dao.StoreDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.StoreDto;
import com.worksap.stm.SWARMS.entity.CustomerClusterEntity;



@Repository
public class KmeanClustering {
	
	int NITER = 100;
	int unusedIndex = 0;;
	@Autowired
	StoreDao storeDao;
	
	List<StoreDto> storeDtoList;
	public CustomerClusterEntity findKMeanCluster(List<CustomerDto> customerDtoList, int k){
		
		ArrayList<HashSet<CustomerDto> > result = new ArrayList<>();
		ArrayList<Geometry> centroids = new ArrayList<>();
		ArrayList<Geometry> remainingCentroidSet = new ArrayList<>();
		 storeDtoList = storeDao.getAllStore();
		int n = customerDtoList.size();
		// Intializing the variable
	
		
		for(int i=1; i<n;i++){
			remainingCentroidSet.add(new Geometry(customerDtoList.get(i).getLng(), customerDtoList.get(i).getLat()));
		}
		
		Geometry g = new Geometry(customerDtoList.get(0).getLng(), customerDtoList.get(0).getLat());
		centroids.add(g);
		
	
		//Intializing the initial centroids
		for(int i=1; i<k; i++){
			Geometry centroid = findCentroid(centroids);
			double maxDist = distance(centroid,remainingCentroidSet.get(0));
			int maxIndex = 0;
			for(int j=1; j<remainingCentroidSet.size(); j++){
				double dist = distance(centroid,remainingCentroidSet.get(j));
				if(dist>maxDist){
					maxIndex = j;
					maxDist = dist;
				}
			}
			
			centroids.add(remainingCentroidSet.get(maxIndex));
			remainingCentroidSet.remove(maxIndex);
		}
		
		int iter = 0;
		while(iter < NITER){
			ArrayList<HashSet<CustomerDto> > temp_result = new ArrayList<>();
			
			for(int i=0; i<k; i++){
				temp_result.add(new HashSet<>());
			}
			for(int i=0; i<n; i++){
				Geometry currentGeometry = new Geometry(customerDtoList.get(i).getLng(), customerDtoList.get(i).getLat());
				double minDist =   distance(currentGeometry, centroids.get(0));
				int minIndex = 0;
				for(int j=1; j<k; j++){
					double dist =  distance(currentGeometry, centroids.get(j));
					if(dist <= minDist){
						minIndex = j;
						minDist = dist;
					}
				}
				if(iter<5)
					System.out.println("i = "+i + "minIndex = "+minIndex+" dist=     "+minDist);
				temp_result.get(minIndex).add(customerDtoList.get(i));
			}
			
			ArrayList<Geometry> temp_centroids = new ArrayList<>();
			for(int j = 0; j<k ; j++){
				temp_centroids.add(findCentroid(temp_result.get(j)));
			}
			
			boolean sameCentroid = true;
			if(iter < 5){
				System.out.println(temp_centroids);
			}
				
			for(int j=0; j<k; j++){
				if(Math.abs(temp_centroids.get(j).lng - centroids.get(j).lng) > 0.00000001 || Math.abs(temp_centroids.get(j).lat - centroids.get(j).lat) > 0.00000001)
					sameCentroid = false;
				
				 centroids.get(j).lng = temp_centroids.get(j).lng;
				 centroids.get(j).lat = temp_centroids.get(j).lat;
			}
			
			if(sameCentroid){
				System.out.println("iter = "+iter);
				ArrayList<Integer> mappedArray = mapStoreToCluster(centroids);
				return new CustomerClusterEntity(temp_result,centroids,generateRadiusList(centroids,temp_result),storeDtoList, mappedArray,unusedIndex);
				
			}
			iter++;
		
			if(iter==NITER){
				System.out.println("iter = "+iter);
				ArrayList<Integer> mappedArray = mapStoreToCluster(centroids);
				return new CustomerClusterEntity(temp_result,centroids,generateRadiusList(centroids,temp_result),storeDtoList,mappedArray, unusedIndex);
				//return temp_result;
			}
		}
	
		return null;
	
	}
	
	
	
	
	private Geometry findCentroid(ArrayList<Geometry> gset){
		
		int length = gset.size();
		Geometry output = new Geometry(0,0);
		for(Geometry g : gset){
			output.lng +=(g.lng/length);
			output.lat +=(g.lat/length);
		}
		
		return output;
	}
	
	private Geometry findCentroid(HashSet<CustomerDto> gset){
		
		int length = gset.size();
		Geometry output = new Geometry(0,0);
		for(CustomerDto g : gset){
			output.lng +=(g.getLng()/length);
			output.lat +=(g.getLat()/length);
		}
		
		return output;
	}
	
	private static double distance(Geometry g1, Geometry g2) {
		double theta = g1.lng - g2.lng;
		double dist = Math.sin(deg2rad(g1.lat)) * Math.sin(deg2rad(g2.lat)) + Math.cos(deg2rad(g1.lat)) * Math.cos(deg2rad(g2.lat)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515*1.609344;
		 // distance in kilometer
		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	private ArrayList<Double> generateRadiusList(ArrayList<Geometry> centroids, ArrayList<HashSet<CustomerDto>> result ){
		
		ArrayList<Double> radius = new ArrayList<Double>();
		for(int i=0; i<centroids.size(); i++){
			HashSet<CustomerDto> customerDtoSet = result.get(i);
			Double dist = 0.0;
			for(CustomerDto cdto : customerDtoSet ){
				Geometry g = new Geometry(cdto.getLng(), cdto.getLat());
				Double ldist = distance(centroids.get(i),g);
				if(ldist>dist)
					dist = ldist;
			}
			radius.add(dist);
		}
		
		return radius;
	}
	
	private ArrayList<Integer> mapStoreToCluster(ArrayList<Geometry> centroids){
		
		ArrayList<Geometry> remainingCentroidSet = new ArrayList<>();
		ArrayList<Integer> indexList = new ArrayList<>();
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i=0; i<centroids.size();i++){
			remainingCentroidSet.add(new Geometry(centroids.get(i).lng, centroids.get(i).lat ));
			indexList.add(i);
		}
		
		for(int i=0; i<storeDtoList.size(); i++){
			Geometry currentGeometry = new Geometry(storeDtoList.get(i).getLng(), storeDtoList.get(i).getLat());
			double minDist =   distance(currentGeometry, remainingCentroidSet.get(0));
			int minIndex = 0;
			for(int j=1; j<remainingCentroidSet.size(); j++){
				double dist =  distance(currentGeometry, remainingCentroidSet.get(j));
				if(dist <= minDist){
					minIndex = j;
					minDist = dist;
				}
			}
			result.add(indexList.get(minIndex));
			indexList.remove(minIndex);
			remainingCentroidSet.remove(minIndex);
		}
		unusedIndex = indexList.get(0);
		
		return result;
		
	}
	
}
