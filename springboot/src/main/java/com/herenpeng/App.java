package com.herenpeng;

import com.herenpeng.entity.User;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;

/**
 * @author herenpeng
 * @since 2021-03-07 20:09
 */
public class App {
    @SneakyThrows
    public static void main(String[] args) {
        Class.forName("com.herenpeng.entity.User");
    }
}
