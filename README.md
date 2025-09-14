# Spring Boot Test Workshop

Spring Boot × JUnit の単体テストを段階的に学ぶためのハンズオン用リポジトリです。Web 層、サービス層、MyBatis リポジトリ層、テンプレート検証、アプリ起動確認までを、最小の実装とテストでカバーします。

- 公式資料（詳細な流れ）: `doc/overview.md`
- スライド要点: `doc/slides.md`
- スピーカーノーツ: `doc/speaker-notes.md`

## 必要環境
- JDK 21
- Maven（このリポジトリは Maven Wrapper `mvnw` を同梱）

## クイックスタート
- すべてのテストを実行
  - `./mvnw test`
- アプリを起動（任意）
  - `./mvnw spring-boot:run`
  - 停止は実行ターミナルで `Ctrl + C`

## エンドポイント（起動時確認用）
- GET `/echo?message=12345` → プレーン文字列をそのまま返却
  - 例: `curl 'http://localhost:8080/echo?message=12345'` → `12345`
- GET `/index?name=yourname` → Thymeleaf テンプレート（デフォルトは `World`）
  - 例: `curl 'http://localhost:8080/index?name=Spring'`
- GET `/add?a=1&b=2` → 1 + 2 の計算結果を返却
  - 例: `curl 'http://localhost:8080/add?a=1&b=2'` → `3`
- GET `/users` → ユーザ一覧（JSON）。H2 in‑memory + MyBatis で取得
  - 例: `curl 'http://localhost:8080/users'`

## テスト実行パターン
- 全体: `./mvnw test`
- 個別クラス: `./mvnw -Dtest=CalcServiceTest test`
- パッケージ配下: `./mvnw -Dtest='dev.mikoto2000.springboot.workshop.controller.*Test' test`

## 何を学べるか（対応ファイル）
- 純粋ユニットテスト（依存なし）
  - `CalcService` / `src/test/java/.../service/CalcServiceTest.java`
- Mockito による依存モックを使ったサービス単体
  - `UserService` / `src/test/java/.../service/UserServiceTest.java`
- Web 層スライス（REST・プレーン文字列）
  - `EchoController` / `src/test/java/.../controller/EchoControllerTest.java`
- テンプレート（Thymeleaf）出力の検証（Jsoup）
  - `IndexController` / `src/test/java/.../controller/IndexControllerTest.java`
- コントローラでサービスをモックし引数検証（ArgumentCaptor）
  - `CalcController` / `src/test/java/.../controller/CalcControllerTest.java`
- JSON レスポンスの簡易検証
  - `UserController` / `src/test/java/.../controller/UserControllerTest.java`
- MyBatis リポジトリのスライステスト（H2 + @Sql）
  - `UserMapper` / `src/test/java/.../repository/UserMapperTest.java`
- アプリケーションコンテキスト起動確認（Smoke）
  - `src/test/java/.../TestApplicationTests.java`

## プロジェクト構成（抜粋）
```
src/
  main/
    java/dev/mikoto2000/springboot/workshop/
      controller/  # Echo, Index, Calc, User
      service/     # CalcService, UserService
      repository/  # UserMapper (MyBatis アノテーション)
      bean/        # User
    resources/
      templates/index.html
      application.yaml
      schema.sql   # H2 サーバテーブル定義
      data.sql     # 起動時の初期データ
  test/
    java/.../controller/*Test.java
    java/.../service/*Test.java
    java/.../repository/*Test.java
    resources/sql/UserMapperTest-findAll.sql
```

## データベース
- H2 in‑memory DB を使用（PostgreSQL 互換モード）。接続は `application.yaml` を参照
- スキーマ定義: `src/main/resources/schema.sql`
- 初期データ: `src/main/resources/data.sql`
- H2 Console: `/h2-console`（起動時のみ）。Driver は `org.h2.Driver`

## トラブルシューティング
- Java/Maven のバージョンを確認: `./mvnw -v`
- ポート競合時: 他のプロセスを停止するか、Spring のポートを変更（`server.port`）
- 依存解決の失敗: ネットワーク環境を確認し、必要であれば再実行

## 参考資料
- ワークショップ概要: `doc/overview.md`
- スライド要点: `doc/slides.md`
- スピーカーノーツ: `doc/speaker-notes.md`

---
このリポジトリのテストは、実務でそのまま使える最小パターンを厳選しています。まずは全テストをグリーンで回し、章ごとに対象ファイルと実行コマンドを確認しながら読み進めてください。
