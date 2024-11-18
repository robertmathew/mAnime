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
//        do {
//            return try await repo.getPopularAnime()
//        } catch {
//            print("Sometimes went wrong")
//            return nil
//        }
        repo.getPopularAnime(completionHandler: { data, error in
            self.animeList = data as! [AnimeListResponse]
        })
    }


}
