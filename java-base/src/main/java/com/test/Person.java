package com.test;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */
@Data
@Builder
public class Person {

    private String name;
    private String address;
    private Integer id;
}
