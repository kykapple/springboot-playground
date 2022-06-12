package com.kykapple.springbootplayground.restdocs.presentation;

import com.kykapple.springbootplayground.restdocs.service.PostService;
import com.kykapple.springbootplayground.restdocs.service.dto.CreatePostRequestDto;
import com.kykapple.springbootplayground.restdocs.service.dto.PostResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void createPostTest() throws Exception {
        // given
        Long postId = 1L;
        given(postService.createPost(any(CreatePostRequestDto.class)))
                .willReturn(postId);

        // when
        ResultActions perform = mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("title", "title")
                .param("contents", "contents"));

        // then
        MvcResult result = perform.andReturn();

        perform.andExpect(status().isCreated());
        assertThat(result.getResponse().getHeader(HttpHeaders.LOCATION))
                .isEqualTo("/api/post/" + postId);

        // restdocs
        perform.andDo(document("createPost",
                requestParameters(
                        parameterWithName("title").description("제목"),
                        parameterWithName("contents").description("내용")
                ),
                responseHeaders(
                        headerWithName(HttpHeaders.LOCATION).description("게시물 접근 주소")
                ))
        );
    }

    @Test
    void getPostTest() throws Exception {
        // given
        Long postId = 1L;
        PostResponseDto postResponseDto = new PostResponseDto("test title", "test contents");

        given(postService.getPost(postId))
                .willReturn(postResponseDto);

        // when
        ResultActions perform = mockMvc.perform(get("/api/posts/{postId}", postId)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(postResponseDto.getTitle()))
                .andExpect(jsonPath("$.contents").value(postResponseDto.getContents()));

        // restdocs
        perform.andDo(document("getPost",
                pathParameters(
                        parameterWithName("postId").description("게시물 id")
                ),
                responseFields(
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("contents").description("내용")
                ))
        );
    }

}
