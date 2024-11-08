import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false

    @ObservedObject var listViewController = ListViewController()

    let columns = [GridItem(.flexible()), GridItem(.flexible())]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns) {
                ForEach(listViewController.animeList, id: \.self) { item in
                    GroupBox {
                        AsyncImage(url: URL(string: item.coverImage)) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(height: 200)
                                .clipped()
                        } placeholder: {
                            ProgressView()
                        }
                        Text(item.title)
                    }
                }
            }
            .padding(.horizontal)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
