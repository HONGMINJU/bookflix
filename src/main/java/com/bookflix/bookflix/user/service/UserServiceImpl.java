package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.library.repository.LibraryRepository;
import com.bookflix.bookflix.library.service.NearLibraryService;
import com.bookflix.bookflix.user.dto.request.PostUserReq;
import com.bookflix.bookflix.user.dto.request.PutUserReq;
import com.bookflix.bookflix.user.dto.response.GetUser;
import com.bookflix.bookflix.user.dto.response.PostUserRes;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.HistoryRepository;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LibraryRepository libraryRepository;

    private final NearLibraryService nearLibraryService;

    private final HistoryService historyService;

    @Override
    public GetUser findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        return GetUser.of(user);
    }

    public void setNearLibraryList(User user){
        user.clearNearLibrary();

        float longitude = user.getLongitude();
        float latitude = user.getLatitude();
        List<Library> allLibraryList = libraryRepository.findAll();
        TreeMap<Float, Long> distanceMap = new TreeMap<Float, Long>();

        for(Library library : allLibraryList){
            float distance = (longitude - library.getLongitude()) * (longitude - library.getLongitude())
                        + (latitude - library.getLatitude()) * (latitude - library.getLatitude());
            distanceMap.put(distance, library.getId());
        }
        for (int i = 0; i< 3; i++) {
            Map.Entry<Float, Long> entry = distanceMap.firstEntry();
            nearLibraryService.createAndAddNearLibrary(user, entry.getValue());
            distanceMap.remove(entry.getKey());
        }
    }

    @Override
    public PostUserRes createUser(Long userId, PostUserReq postUserReq){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        user.updateInfo(postUserReq.getNickname(), postUserReq.getGender(), postUserReq.getAge(),
                postUserReq.getRegion(), postUserReq.getLongitude(), postUserReq.getLatitude());

        // historyList 갱신
        for (String isbn : postUserReq.getBookList()) {
            historyService.createAndAddHistory(user, isbn);
        }

        // libraryList 갱신
        setNearLibraryList(user);

        // TODO : recommendList 갱신
        return PostUserRes.of(user);
    }

    @Override
    public void updateUser(Long userId, PutUserReq putUserReq) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        user.updateInfo(putUserReq.getNickname(), putUserReq.getGender(), putUserReq.getAge(),
                putUserReq.getRegion(), putUserReq.getLongitude(), putUserReq.getLatitude());

        // libraryList 갱신
        setNearLibraryList(user);
    }
}
