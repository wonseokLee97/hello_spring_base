package hello.hellospring.repository;

import hello.hellospring.domian.Member;
import org.springframework.stereotype.Repository;

import java.util.*;



@Repository // with autowired
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
         return store.values().stream()
                 .filter(member -> member.getName().equals(name))
                 .findAny(); // 하나라도 찾으면
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member 들을 list 형태로 반환
    }

    public void clearStore(){
        store.clear();
    }
}
