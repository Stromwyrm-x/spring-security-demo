package com.weng.springsecuritydemo.mapper;

import com.weng.springsecuritydemo.entity.Menu;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuMapperTest
{
    @Resource
    private MenuMapper menuMapper;
    @Test
    public void testSelectMenuByRoleIds()
    {
        List<Long> roleIds=new ArrayList<>();
        roleIds.add(1L);
        roleIds.add(2L);
        List<Menu> menus = menuMapper.selectMenuByRoleIds(roleIds);
        System.out.println(menus);
    }
}