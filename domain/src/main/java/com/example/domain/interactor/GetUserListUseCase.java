package com.example.domain.interactor;

import com.example.domain.Repository.UserRepository;
import com.example.domain.User;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    /*public GetUserListUseCase(final UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }*/

    @Override
    Observable<List<User>> buildUseCaseObservable(Void aVoid) {
        return this.userRepository.users();
    }
}
