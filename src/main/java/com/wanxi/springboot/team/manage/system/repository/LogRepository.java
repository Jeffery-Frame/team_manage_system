package com.wanxi.springboot.team.manage.system.repository;

import com.wanxi.springboot.team.manage.system.esModel.EsLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<EsLog,Long> {
}
