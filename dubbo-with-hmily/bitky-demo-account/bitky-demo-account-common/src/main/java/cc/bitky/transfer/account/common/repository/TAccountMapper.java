package cc.bitky.transfer.account.common.repository;

import cc.bitky.transfer.account.common.repository.entity.TAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */

@Mapper
public interface TAccountMapper {

    @Select("SELECT * FROM ${tableName} WHERE uid = #{uid}")
    List<TAccount> selectByUid(String tableName, String uid);

    @Update("UPDATE ${tableName} SET amount = amount + ${account.amount} WHERE uid = #{account.uid}")
    void updateAmount(String tableName, TAccount account);
}
