package service;

import data.DbContext;

public abstract class BaseService{
    DbContext context;
    BaseService(){
        context = DbContext.getInstance();
    }


}
