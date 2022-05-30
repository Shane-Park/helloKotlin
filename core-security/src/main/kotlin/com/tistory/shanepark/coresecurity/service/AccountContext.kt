package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.entity.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AccountContext(val account: Account, authorities: MutableCollection<out GrantedAuthority>?) :
    User(account.username, account.password, authorities) {
}
