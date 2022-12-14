/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.service;

import com.hazelcast.core.HazelcastInstance;
import it.poste.hazelcast.datagrid.config.Config;
import it.poste.hazelcast.datagrid.model.Storable;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */
@Service
public class DistributedStore {
    
    private final HazelcastInstance hazelcastInstance;
    private final String mapName;
    
    public DistributedStore(HazelcastInstance hi,String mapName) {
        this.hazelcastInstance = hi;
        this.mapName = mapName;        
    }
    
    private ConcurrentMap<String,String> map() {
        return this.hazelcastInstance.getMap(mapName);
    }
    
    public Storable get(String key) {
        return Storable
                .builder()
                .key(key)
                .value(map().get(key))
                .build();
    }
    
    public void put(Storable st) {
        map().put(st.getKey(),st.getValue());
    }
    
}
