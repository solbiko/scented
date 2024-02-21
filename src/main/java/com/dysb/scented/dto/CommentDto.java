package com.dysb.scented.dto;

import com.dysb.scented.entity.Comment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentDto {

    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @JsonInclude
    @Schema(description = "향수 id")
    private Long perfumeId;

    public CommentDto(Comment comment) {
        if (comment == null) {
            return;
        }
        this.id = comment.getId();
        this.title = comment.getTitle();
        this.content = comment.getContent();
        this.perfumeId = comment.getPerfume().getId();
    }

    public static List<CommentDto> by(List<Comment> commentList) {
        List<CommentDto> r = new ArrayList<>();
        if (commentList != null) {
            commentList.forEach(entity -> r.add(new CommentDto(entity)));
        }
        return r;
    }


}
