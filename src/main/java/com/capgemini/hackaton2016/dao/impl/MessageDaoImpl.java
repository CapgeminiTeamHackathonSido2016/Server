package com.capgemini.hackaton2016.dao.impl;

import com.capgemini.hackaton2016.dao.MessageDao;
import com.capgemini.hackaton2016.model.Message;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author afbustamante
 */
@RequestScoped
public class MessageDaoImpl implements MessageDao {
    
    @Resource
    private EntityManager em;

    @Override
    public void insererMessage(Message msg) {
        try {
            em.persist(msg);
        } catch (Exception e) {
        }
    }
    
}
