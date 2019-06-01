package com.web;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.sms.bean.User;
import com.google.common.collect.Maps;
import com.service.AggregationService;

import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

/**
 * 聚合多个 请求
 * 
 * @author Administrator
 *
 */
@RestController
public class AggregationController {
	public static final Logger logger = LoggerFactory.getLogger(AggregationController.class);

	@Autowired
	private AggregationService aggregationService;

	@GetMapping("/aggregate/{id}")
	public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
		Observable<HashMap<String, User>> result = this.aggregateObservable2(id);
		return this.toDeferredResult(result);
	}

	/**
	 * jdk 1.8 
	 * @param id
	 * @return
	 */
	/*public Observable<HashMap<String, User>> aggregateObservable(Long id) {
		return Observable.zip(this.aggregationService.getUserById(id), 
							  this.aggregationService.getMovieUserByUserId(id),
							  (user,movieUser)->{
								  HashMap<String, User> map = Maps.newHashMap();
									map.put("user", user);
									map.put("movieUser", movieUser);
									return map;
							  }
				);
	}*/
	
	/**
	 * jdk 1.7 
	 * @param id
	 * @return
	 */
	public Observable<HashMap<String, User>> aggregateObservable2(Long id) {
		Observable<User> userObservable = aggregationService.getUserById(id);
		Observable<User> movieUserObservable = aggregationService.getMovieUserByUserId(id);
		 Func2 zipFunction = new Func2<User, User, User>() {
			@Override
			public User call(User t1, User t2) {
				 HashMap<String, User> map = Maps.newHashMap();
					map.put("user", t1);
					map.put("movieUser", t2);
				return null;
			}
			 
		};
		return Observable.zip(userObservable, movieUserObservable, zipFunction);
	}

	public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
		final DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
		// 订阅
		details.subscribe(new Observer<HashMap<String, User>>() {

			@Override
			public void onCompleted() {
				logger.info("完成...");
			}

			@Override
			public void onError(Throwable e) {
				logger.error("发生错误...", e);
			}

			@Override
			public void onNext(HashMap<String, User> movieDetails) {
				result.setResult(movieDetails);
			}
		});
		return result;
	}
}
