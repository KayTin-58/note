package com.tool;

import lombok.Data;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */
@Data
public class Tuple<A,B> {
    private A first;
    private B second;
}
