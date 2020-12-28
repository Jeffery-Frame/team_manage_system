package com.wanxi.springboot.team.manage.system.repository;


import com.wanxi.springboot.team.manage.system.esModel.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<EsUser,Long> {
}
