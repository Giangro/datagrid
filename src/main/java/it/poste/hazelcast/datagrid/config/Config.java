/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.config;

import com.hazelcast.core.HazelcastInstance;
import it.poste.hazelcast.datagrid.node.NodeCandidate;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.hazelcast.leader.LeaderInitiator;
import org.springframework.integration.leader.Candidate;

/**
 *
 * @author alex
 */
@Configuration
@Slf4j
public class Config {

    private final String nodeId;
    private final String role;
    private final String distributedMapName;

    public Config(@Value("${app.nodeId}") String nodeid,
            @Value("${app.role}") String role,
            @Value("${app.mapName}") String map
    ) {
        this.nodeId = nodeid;
        this.role = role;
        this.distributedMapName = map;
    }

    @Bean
    public String nodeId() {
        return nodeId;
    }

    @Bean
    public String role() {
        return role;
    }

    @Bean
    public String mapName() {
        return distributedMapName;
    }

    @Bean
    public Candidate nodeCandidate() {
        final NodeCandidate candidate
                = new NodeCandidate(nodeId, role);
        return candidate;
    }

    @Bean
    public LeaderInitiator initiator(HazelcastInstance hazelcastInstance) {
        final LeaderInitiator leaderInitiator
                = new LeaderInitiator(hazelcastInstance, nodeCandidate());
        return leaderInitiator;
    }

    @PostConstruct
    private void postConfiguration() {
        log.info("Node Id \"{}\" has been configured. Map Name \"{}\"", nodeId, distributedMapName);
    }

}
