//
//  AnimeListViewController.swift
//  iosApp
//
//  Created by Robert Mathew [Litmus7] on 15/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Shared

class AnimeListController: ObservableObject {

    let repo = DiHelper().getAnimeRepository()

    @Published var animeList: [AnimeListResponse] = []

    init() {
        getPopularAnimeData()
    }

    func getPopularAnimeData() {
        repo.getPopularAnime(completionHandler: { data, error in
            self.animeList = data as! [AnimeListResponse]
        })
    }


}
