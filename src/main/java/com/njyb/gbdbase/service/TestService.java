package com.njyb.gbdbase.service;

import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService {
    
	@Override
	public void test(String name,String love) {
		// TODO Auto-generated method stub
		System.out.println(name+","+love);
	}

}
