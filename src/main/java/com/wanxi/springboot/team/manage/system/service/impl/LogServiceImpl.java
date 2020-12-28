package com.wanxi.springboot.team.manage.system.service.impl;

import com.wanxi.springboot.team.manage.system.model.Log;
import com.wanxi.springboot.team.manage.system.mapper.LogMapper;
import com.wanxi.springboot.team.manage.system.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
