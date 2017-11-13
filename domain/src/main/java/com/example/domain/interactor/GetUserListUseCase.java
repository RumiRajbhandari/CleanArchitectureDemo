package com.example.domain.interactor;

import android.util.Log;

import com.example.domain.Repository.UserRepository;
import com.example.domain.User;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/5/17.
 */

public class GetUserListUseCase extends UseCase<List<User>,Void> {


    private final UserRepository userRepository;

    @Inject
    public GetUserListUseCase(final UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }

    @Override
    Observable<List<User>> buildUseCaseObservable(Void aVoid) {
       return Observable.concat(this.userRepository.user(2),this.userRepository.getNetData())
                .firstElement()
                .toObservable();
    }



}
