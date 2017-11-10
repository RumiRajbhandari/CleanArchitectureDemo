import com.example.data.Entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by root on 11/6/17.
 */

public interface UserDataStore {
    Observable<List<UserEntity>> users();

}
