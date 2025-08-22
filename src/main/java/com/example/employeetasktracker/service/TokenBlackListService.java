package com.example.employeetasktracker.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlackListService {

    private final Set<String> blackList = ConcurrentHashMap.newKeySet();

    public void blackListToken(String token){
        blackList.add(token);
    }

    public boolean isTokenBlackList(String token){
        return blackList.contains(token);
    }

}
