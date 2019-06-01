package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sms.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;
import rx.Subscriber;

/**
 * 使用zuul聚合微服务
 * @author Administrator
 *
 */
@Service
public class AggregationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//lamda写法
	/*public Observable<User> getUserById2(final Long id){
		//创建一个被观察者
		return Observable.create(observer -> {
			User user = restTemplate.getForObject("http://userservice/user/{id}", User.class,id);
			observer.onNext(user);
			observer.onCompleted();
		});
	}*/
	@HystrixCommand(fallbackMethod = "fallback")
	public Observable<User> getUserById(final Long id){
		Observable<User> observable = Observable.create(new Observable.OnSubscribe<User>() {
		    @Override
		    public void call(Subscriber<? super User> subscriber) {
		    	//用户微服务的/{id}
		    	User user = restTemplate.getForObject("http://userservice/user/{id}", User.class,id);
		    	subscriber.onNext(user);
		        subscriber.onCompleted();
		    }
		});
		return observable;
	}	
	
	@HystrixCommand(fallbackMethod = "fallback")
	public Observable<User> getMovieUserByUserId(final Long id){
		Observable<User> observable = Observable.create(new Observable.OnSubscribe<User>() {
			@Override
			public void call(Subscriber<? super User> subscriber) {
				//电影微服务的user/{id}
				User user = restTemplate.getForObject("http://movieservice/user/{id}", User.class,id);
		    	subscriber.onNext(user);
		        subscriber.onCompleted();
			}
		});
		return observable;
	}
	
	public User fallback(Long id){
		User user = new User();
		user.setId(-1L);
		user.setName("默认用户");
		user.setAge(18);
		return user;
	}
}
