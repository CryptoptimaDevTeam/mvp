package CryptOptima.server.domain.role.service;

import CryptOptima.server.domain.role.dto.ResourceDto;

import java.util.List;

public interface ResourceService {

    /* 1. resource 등록
        - resource 에 대한 정보와 List<Long> roleIds 를 받는다.
        - 참조무결성을 지키기 위해 resource를 먼저 저장, ResourceRole(resource, findByRoleId())를 생성하여 list.add 저장
     */
    void createResource(ResourceDto.Create dto);

    /* 2. resource 수정
        ※ resourceRole.setResource(), setRole() 해줘야 한다. 이게 메인이다!
        - resourceRole 추가 (resource.resourceRoleList.add(resourceRole))
        - resourceRole 제거 (resource 쪽에서 orphanRemoval=true 로 설정하면 List.remove 했을 때 resourceRole 엔티티가 삭제됨.)
     */

    /* 3. resource 삭제
        - delete One
     */

    /* 4. resource 조회
        - resource 리스트
     */
    List<ResourceDto.Response> getResources();
    ResourceDto.Response getResource(Long resourceId);
}
