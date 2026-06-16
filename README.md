# UnityWallpaperSample

Unity をライブ壁紙（Live Wallpaper）として表示するだけの、必要最小限の Android サンプルアプリです。
アプリ画面のボタンをタップすると、Unity を描画する `WallpaperService` を壁紙として設定できます。

## 機能

- 「Unityを壁紙に設定」ボタンからシステムのライブ壁紙プレビューを起動し、ワンタップで適用
- 壁紙として Unity シーンをホーム/ロック画面の背景に描画

## 構成

```
app/src/main/java/unity/wallpaper/sample/
├── MainActivity.kt              # 壁紙設定ボタンを表示するだけの Activity
├── ui/main/MainScreen.kt        # ボタン UI ＋ ライブ壁紙プレビュー起動
└── unity/
    ├── UnityManager.kt          # UnityPlayer を保持しサーフェスへ描画する最小シングルトン
    └── UnityWallpaperService.kt # Unity を描画する WallpaperService
```

## 必要要件

- `minSdk = 25`（Unity AAR の要件）
- 対応 ABI: **arm64-v8a のみ**（実機 or arm64 エミュレータが必要）
- Unity ライブラリは `libs/unity/unityLibrary-release.aar` を `implementation(files(...))` で参照

> Unity AAR は [withmo](https://github.com/kei-1111/withmo) プロジェクトでビルドされた `unityLibrary-release.aar` を利用しています。
> そのため壁紙には withmo の Unity シーンが表示されます。独自シーンに差し替える場合は、この AAR を入れ替えてください。

## ビルド & 実行

```bash
./gradlew assembleDebug        # デバッグ APK をビルド
./gradlew installDebug         # 接続中の端末にインストール
./gradlew testDebugUnitTest    # ユニットテスト
```

インストール後、アプリを起動してボタンをタップ → ライブ壁紙プレビューで「壁紙に設定」を選ぶと適用されます。
