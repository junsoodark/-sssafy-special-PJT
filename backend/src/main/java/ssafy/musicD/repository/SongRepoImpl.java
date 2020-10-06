package ssafy.musicD.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ssafy.musicD.dto.Song;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepoImpl implements SongRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Song> searchSong(String keyword) {
        Query query = new Query(Criteria.where("album_name").regex(keyword));
        System.out.println(keyword);
        List<Song> keywordSong = mongoTemplate.find(query, Song.class, "songs");
        return keywordSong;
    }


    @Override
    public <S extends Song> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Song> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Song> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public Iterable<Song> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Song entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Song> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Song> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Song> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Song> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Song> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Song> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Song> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Song> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Song> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Song> boolean exists(Example<S> example) {
        return false;
    }
}
