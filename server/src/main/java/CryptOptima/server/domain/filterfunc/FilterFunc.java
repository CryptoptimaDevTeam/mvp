package CryptOptima.server.domain.filterfunc;

import javax.persistence.*;

@Entity
public class FilterFunc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filterFuncId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String filterFuncName;

    // TODO filterfunc 저장 시 null인지 유효성 검사
    @Column(nullable = false)
    private String filterFunc;

    // TODO 특정 유저의 북마크 조건식 조회 시, select where userId=? AND isBookmark=true 쿼리 vs. User left join BookmarkFilterFunc
    // TODO 조건식 북마크 등록/취소 시, filterFuncId로 조회 & isBookmark 업데이트 vs. 등록 or BookmarkFilterFunc 테이블에서 filterFuncId로 조회, 삭제
    @Column(nullable = false)
    private boolean isBookmark;

    // 조건식을 북마크한다, 취소한다
    public void bookmarkFilterFunc() {
        this.isBookmark = true;
    }

    public void cancelBookmarkFilterFunc() {
        this.isBookmark = false;
    }

    // 조건식 이름을 변경한다.
    public void changeFilterFuncName(String newFilterFuncName) {
        this.filterFuncName = newFilterFuncName;
    }

    // 조건식을 변경한다.
    public void changeFilterFunc(String newFilterFunc) {
        this.filterFunc = newFilterFunc;
    }
}
