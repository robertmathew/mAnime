//
//  AnimeDetailView.swift
//  iosApp
//
//  Created by Robert Mathew [Litmus7] on 15/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AnimeDetailView: View {
    var animeId: Int32
    
    @ObservedObject var controller = AnimeDetailController()
    
//    var body: some View {
//        VStack {
//            AsyncImage(url: URL(string: controller.animeDetail?.bannerImage!)) { image in
//                image.resizable()
//            } placeholder: {
//                ProgressView()
//            }
//            
//            Text(controller.animeDetail?.title ?? "")
//        }
//        .onAppear {
//            controller.getAnimeDetailData(id: animeId)
//        }
//    }
    
    var body: some View {
            VStack {
                if let bannerImageURL = controller.animeDetail?.bannerImage, let url = URL(string: bannerImageURL) {
                    AsyncImage(url: url) { image in
                        image.resizable()
                            .scaledToFit()
                            .frame(maxWidth: .infinity)
                    } placeholder: {
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle())
                    }
                }
                
                Text(controller.animeDetail?.title ?? "Loading...")
                    .font(.headline)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding()
                
                Spacer()
            }
            .onAppear {
                controller.getAnimeDetailData(id: animeId)
            }
        
        }
}


struct AnimeDetailView_Previews: PreviewProvider {
    static var previews: some View {
        AnimeDetailView(animeId: 154587)
    }
}
