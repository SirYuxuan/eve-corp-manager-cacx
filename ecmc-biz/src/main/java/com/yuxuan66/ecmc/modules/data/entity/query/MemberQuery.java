package com.yuxuan66.ecmc.modules.data.entity.query;

import com.yuxuan66.ecmc.modules.data.entity.Member;
import com.yuxuan66.ecmc.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/12/24
 */
@Data
public class MemberQuery extends BaseQuery<Member> {

    private Integer day;
    private Boolean corpSystem;
    private Boolean seatSystem;

}
