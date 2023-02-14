package com.example.webclient.Service;

import com.example.webclient.Service.ProfileRepository;
import com.example.webclient.Service.ProfileService;
import com.example.webclient.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    public ProfileServiceImpl ( ProfileRepository profileRepository ) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Mono<Profile> findProfileById ( String id ) {
        return profileRepository.findById(id);
    }

    @Override
    public Flux<Profile> findAll () {
        return profileRepository.findAll();
    }

    @Override
    public Mono<Profile> save ( Profile profile ) {
        return profileRepository.save(profile);
    }

    @Override
    public Mono<Void> delete ( Profile profile ) {
        return profileRepository.delete(profile);
    }

    @Override
    public Mono<Profile> findProfileByName ( String name ) {
        return profileRepository.getProfileByName(name).defaultIfEmpty(new Profile());
    }
}
