package am.tech42.videmodemo.services;

import am.tech42.videmodemo.model.CommentsAndReactions.Reactions;
import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.repositories.ReactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {
    @Autowired
    ReactRepo reactRepo;

    public List<Reactions> VideoRectList(Video video) { return reactRepo.findAllByVideo(video); }
    public List<Reactions> UserReactList(User user) { return reactRepo.findAllByUser(user); }
}
