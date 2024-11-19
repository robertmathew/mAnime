import SwiftUI
import Shared

struct AnimeListView: View {
    @State private var showContent = false
    
    @ObservedObject var controller = AnimeListController()
    
    let columns: [GridItem] = Array(repeating: GridItem(.flexible(), alignment: .center), count: 3)
    
    var body: some View {
        NavigationView {
            ScrollView {
                LazyVGrid(columns: columns, spacing: 20) {
                    ForEach(controller.animeList, id: \.self) { item in
                        NavigationLink(destination: AnimeDetailView()) {
                            VStack {
                                AsyncImage(url: URL(string: item.coverImage)) { image in
                                    image
                                        .resizable()
                                        .aspectRatio(contentMode: .fit)
                                        .cornerRadius(8)
                                } placeholder: {
                                    ProgressView()
                                }
                                Text(item.title).font(.caption).lineLimit(1)
                            }
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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        AnimeListView()
    }
}
