package com.zhy.pattern.templateMethod;

import com.zhy.pattern.templateMethod.dao.MemberDao;

public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        memberDao.query();
    }
}
