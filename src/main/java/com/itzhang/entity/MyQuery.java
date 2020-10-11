package com.itzhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyQuery {
    Object key;
    Integer start;
    Integer offset;
}
