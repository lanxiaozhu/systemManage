//api.js
import service from './request.js'
import qs from 'qs'

export const getInfo = function(url){
    return service({
        url: url,
        method: 'get',
        // data: null
    })
};
export const getParam = function(url,data){
    return service({
        url: url,
        method: 'get',
        params: data,
    })
};
export const postParam = function(url,data){
    return service({
        url: url,
        method: 'post',
        data: data
    })
};

export const delParam = function(url,params){
    return service( 
         axios.delete(url, {
         params: params,
         paramsSerializer: params => {
            return qs.stringify(params, { indices: false })
        }
        })
    )
};

export const putParam = function(url,data){
    return service({
        url: url,
        method: 'put',
        data: data
    })
};

