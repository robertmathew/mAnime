//
//  AnimeDetailController.swift
//  iosApp
//
//  Created by Robert Mathew [Litmus7] on 22/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Shared

class AnimeDetailController: ObservableObject {

    let repo = DiHelper().getAnimeRepository()

    @Published var animeDetail: AnimeDetailResponse?

    func getAnimeDetailData(id: Int32) {
        
        repo.getAnimeDetails(animeId: Int32(id), completionHandler: { data, error in
            self.animeDetail = data!
        })
    }


}
