import SwiftUI
import Shared

struct AnimeListView: View {

    @ObservedObject var controller = AnimeListController()
    
    let columns: [GridItem] = Array(repeating: GridItem(.flexible(), alignment: .center), count: 3)
    
    var body: some View {
        NavigationView {
            ScrollView {
                LazyVGrid(columns: columns, spacing: 20) {
                    ForEach(controller.animeList, id: \.self) { item in
                        NavigationLink(
                            destination: AnimeDetailView(animeId: item.id)
                        ) {
                            ItemView(title: item.title, image: item.coverImage)
                        }
                        .buttonStyle(PlainButtonStyle())
                    }
                }
            }
            .navigationBarTitle(Text("mAnime"), displayMode: .inline)
            .padding(.horizontal)
        }
    }
}

struct ItemView: View {
    var title: String
    var image: String
    
    var body: some View {
        VStack {
            AsyncImage(url: URL(string: image)) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .cornerRadius(8)
            } placeholder: {
                ProgressView()
            }
            Text(title).font(.caption).lineLimit(1)
        }
    }
}

struct AnimeListView_Previews: PreviewProvider {
    static var previews: some View {
        AnimeListView()
    }
}
