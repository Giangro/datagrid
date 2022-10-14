/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * @author alex
 */
@Builder
@RequiredArgsConstructor
//@Accessors(fluent = true)
@Getter
@Jacksonized
public class Storable {
    private final @NonNull String key;
    private final @NonNull String value;
}
