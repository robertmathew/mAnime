//
//  AnimeDetailView.swift
//  iosApp
//
//  Created by Robert Mathew [Litmus7] on 15/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct AnimeDetailView: View {
    var animeId: Int32
    
    @ObservedObject var controller = AnimeDetailController()
    
    
    var body: some View {
        VStack {
            if let bannerImageURL = controller.animeDetail?.bannerImage, let url = URL(string: bannerImageURL) {
                WebImage(url: url)
                    .resizable()
                    .scaledToFit()
                    .frame(maxWidth: .infinity)
            }
            
            Text(controller.animeDetail?.title ?? "Loading...")
                .font(.headline)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding()
            
            Text("Description")
                .font(.subheadline)
                .fontWeight(.bold)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding([.horizontal], 16)
            
            Text(controller.animeDetail?.description_ ?? "")
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding([.horizontal], 16)
                .padding([.top], 8)
            
            Text("Character")
                .font(.subheadline)
                .fontWeight(.bold)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding([.horizontal, .top], 16)
            
            if let characters = controller.animeDetail?.character {
                List(characters, id: \.self) { item in
                    CharacterListView(character: item)
                }
                .listStyle(.plain)
            } else {
                // Handle the case when character is nil (e.g., show a placeholder or error message)
            }
        }
        .onAppear {
            controller.getAnimeDetailData(id: animeId)
        }
        
    }
}

struct CharacterListView: View {
    var character: Character
    
    var body: some View {
        
        HStack {
            HStack {
                WebImage(url: URL(string: character.image))
                    .resizable()
                    .scaledToFit()
                    .cornerRadius(4)
                
                VStack(alignment: .leading, spacing: 4) {
                    Text(character.name)
                        .font(.body)
                    Text(character.role)
                        .font(.caption)
                        .foregroundColor(.gray)
                }
            }
            
            Spacer()
            
            
            HStack {
                VStack(alignment: .trailing, spacing: 4) {
                    Text(character.voiceActor.name)
                        .font(.body)
                    Text(character.voiceActor.language)
                        .font(.caption)
                        .foregroundColor(.gray)
                }
                
                WebImage(url: URL(string: character.voiceActor.image))
                    .resizable()
                    .scaledToFit()
                    .cornerRadius(4)
            }
        }
        .frame(height: 62)
    }
}



struct AnimeDetailView_Previews: PreviewProvider {
    static var previews: some View {
        AnimeDetailView(animeId: 154587)
    }
}
