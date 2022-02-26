package com.dong.servicebase.exceptionhandler;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ApiModelProperty使用场景
//使用在被 @ApiModel 注解的模型类的属性上
//概述
//添加和操作模型属性的数据
@Data
@AllArgsConstructor //生成有参数构造方法
@NoArgsConstructor  //生成无参数构造方法
public class GuliException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码
    private String msg;//异常信息
}
