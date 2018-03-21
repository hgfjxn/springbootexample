package win.hgfdodo.mybatisconfig.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import win.hgfdodo.mybatisconfig.demo.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getUsers(String name);
}

