package com.sist.dao;

import lombok.Data;
// 테이블 마다 vo를 만든다

@Data
public class DeptVO {
  private int deptno;
  private String dname,loc;
}
