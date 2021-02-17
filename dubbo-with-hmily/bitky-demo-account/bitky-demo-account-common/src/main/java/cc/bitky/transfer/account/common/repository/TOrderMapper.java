package cc.bitky.transfer.account.common.repository;

import cc.bitky.transfer.account.common.repository.entity.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */

@Mapper
public interface TOrderMapper {

    @Select("SELECT * FROM t_order")
    List<TOrder> selectAll();

    @Select("SELECT * FROM t_order WHERE biz_serial = #{bizSerial}")
    List<TOrder> selectByBizSerial(String bizSerial);

    @Insert("INSERT INTO t_order(`uid`, `amount`, `biz_serial`, `account_type`) VALUES (#{uid}, #{amount}, #{bizSerial}, #{accountType})")
    void insert(TOrder user);

    @Update("UPDATE t_order SET finished = #{finished} WHERE biz_serial = #{bizSerial}")
    void updateStatus(Boolean finished, String bizSerial);

}
