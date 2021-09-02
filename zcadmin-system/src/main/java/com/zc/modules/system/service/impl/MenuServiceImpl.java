package com.zc.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zc.modules.system.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.system.mapper.MenuMapper;
import com.zc.modules.system.entity.Menu;
import com.zc.modules.system.service.MenuService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统菜单 服务层实现
 *
 * @author zhangc
 * @date 2021-09-01
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<MenuVO> selectMenuVO() {
        List<Menu> menus = menuMapper.selectListBySelective(new Menu());
        List<MenuVO> menuVOS=new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getPid()==null){
                MenuVO menuVO=new MenuVO();
                BeanUtils.copyProperties(menu,menuVO);
                menuVO.setChildren(new ArrayList<>());
                menuVOS.add(menuVO);
            }
        }
        for (MenuVO menuVO : menuVOS) {
            setChild(menus,menuVO);
        }
        return menuVOS;
    }


    public void setChild(List<Menu> menus,MenuVO menuVO){
        for (Menu menu : menus) {
            if (menuVO!=null&&menu.getPid()==menuVO.getMenuId()){
                List<MenuVO> child = menuVO.getChildren();

                MenuVO build = new MenuVO();
                build.setChildren(new ArrayList<>());
                BeanUtils.copyProperties(menu,build);
                child.add(build);

                setChild(menus,build);
            }
        }
    }



    /**
     * 查询系统菜单信息
     *
     * @param id 系统菜单ID
     * @return 系统菜单信息
     */
    @Override
    public Menu selectByPrimaryKey(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询系统菜单列表
     *
     * @param record 系统菜单信息
     * @return 系统菜单集合
     */
    @Override
    public List<Menu> selectListBySelective(Menu record) {
        return menuMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个系统菜单对象(一般用于条件可以确定唯一数据)
     *
     * @param record 系统菜单信息
     * @return 系统菜单
     */
    @Override
    public Menu selectOneBySelective(Menu record) {
        return menuMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询系统菜单列表
     *
     * @param record 系统菜单信息
     * @param page mybatis-plus 分页对象
     * @return 系统菜单集合
     */
    @Override
    public IPage<Menu> selectPageBySelective(Menu record, Page page) {
        return menuMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询系统菜单列表
     *
     * @param ids 系统菜单主键List集合
     * @return 系统菜单集合
     */
    @Override
    public List<Menu> selectByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return menuMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 系统菜单 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(Menu record) {
        return menuMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 系统菜单 信息
     * @return 插入数量
     */
    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 系统菜单 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 系统菜单集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<Menu> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Menu>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Menu> records : list) {
            int count = menuMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 系统菜单 信息
     * @return 修改数量
     */
    @Override
    public int update(Menu record) {
        return menuMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 系统菜单 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(Menu record) {
        return menuMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(Menu params,Menu record) {
        return menuMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 系统菜单 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<Menu> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Menu>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Menu> records : list) {
            int count = menuMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 系统菜单 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<Menu> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<Menu>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<Menu> records : listLists) {
            int count = menuMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }

    /**
    * 根据条件删除数据
    *
    * @param record  删除的条件
    * @return 删除数量
    */
    public int deleteBySelective(Menu record){
        return menuMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 系统菜单 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 系统菜单 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return menuMapper.deleteByPrimaryKeys(ids);
    }

}
