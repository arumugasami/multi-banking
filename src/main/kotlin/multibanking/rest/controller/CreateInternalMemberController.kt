package multibanking.rest.controller

import multibanking.dto.MemberDto
import multibanking.token.TokenMember
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CreateInternalMemberController(private val tokenMember: TokenMember) {

    @PostMapping("/internal/createmember")
    fun createInternalMember(@Valid @RequestBody memberDto: MemberDto): ResponseEntity<Unit> {
        tokenMember.createMember(memberDto.email)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}
