package com.example.socialcomposeapp.di

import com.example.socialcomposeapp.data.repository.UserRepository
import com.example.socialcomposeapp.screens.editprofile.EditProfileViewModel
import com.example.socialcomposeapp.screens.message.MessageViewModel
import com.example.socialcomposeapp.screens.publicprofile.PublicProfileViewModel
import com.example.socialcomposeapp.screens.search.SearchScreenViewModel
import com.example.socialcomposeapp.screens.userprofile.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MessageViewModel)
    single { UserRepository() }
    viewModel { EditProfileViewModel(get()) }
    viewModel { ProfileScreenViewModel(get()) }
    viewModel { SearchScreenViewModel(get()) }
    viewModel { PublicProfileViewModel(get())}

}