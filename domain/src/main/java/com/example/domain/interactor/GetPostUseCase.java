package com.example.domain.interactor;

import android.util.Log;

import com.example.domain.Post;
import com.example.domain.Repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/7/17.
 */

public class GetPostUseCase extends UseCase<List<Post>,GetPostUseCase.Params> {
    private final UserRepository userRepository;
    @Inject
    public GetPostUseCase(final UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }

    @Override
    Observable<List<Post>> buildUseCaseObservable(Params params) {
        return Observable.concat(this.userRepository.posts(params.id),this.userRepository.getNetDataForPost(params.id))
                .firstElement()
                .toObservable();
    }



    public static final class Params{
       private final int id;
       private Params(int id){
           this.id=id;
       }
       public static Params forPost(int id){
           return new Params(id);
       }
   }


}
