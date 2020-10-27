package am.tech42.videmodemo.services;

import am.tech42.videmodemo.model.Actions.ActionsValue;
import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.repositories.ActionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActionService {
    @Autowired
    ActionsRepo actionsRepo;

    public List<UserActions> getUserActions(User user) {
        return actionsRepo.findAllByUser(user);
    }

    @Transactional
    public void addAction(User user, Video video, String actionValue) {
        UserActions userAction = new UserActions();
        userAction.setUser(user);
        userAction.setVideo(video);
        userAction.setActionsValue(actionValue);
        userAction.setLocalDateTime(LocalDateTime.now());
        actionsRepo.save(userAction);

    }
}
