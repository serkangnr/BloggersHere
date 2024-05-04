
## User Class

- **id**: Kullanıcının benzersiz kimliğini temsil eder.
- **name**: Kullanıcının adını içerir.
- **lastname**: Kullanıcının soyadını içerir.
- **email**: Kullanıcının e-posta adresini içerir. Sadece Gmail adreslerine izin verilir.
- **password**: Kullanıcının şifresini içerir. 8 karakterden oluşmalıdır.
- **posts**: Kullanıcının oluşturduğu blog yazılarını içeren bir liste.
- **comments**: Kullanıcının yaptığı yorumları içeren bir liste.
- **likes**: Kullanıcının beğendiği blog yazılarını içeren bir liste.

## Categories Class

- **id**: Kategorinin benzersiz kimliğini temsil eder.
- **name**: Kategorinin adını içerir.
- **description**: Kategorinin açıklamasını içerir.
- **posts**: Bu kategoriye ait blog yazılarını içeren bir liste.

## Post Class

- **id**: Yazının benzersiz kimliğini temsil eder.
- **title**: Yazının başlığını içerir.
- **content**: Yazının içeriğini içerir.
- **published_at**: Yazının yayımlanma tarihini içerir.
- **user**: Yazıyı oluşturan kullanıcıyı temsil eder.
- **categories**: Yazının ait olduğu kategoriyi temsil eder.
- **comments**: Yazıya yapılan yorumları içeren bir liste.
- **users**: Yazıyı beğenen kullanıcıları içeren bir liste.

## Comment Class

- **id**: Yorumun benzersiz kimliğini temsil eder.
- **content**: Yorumun içeriğini içerir.
- **date**: Yorumun oluşturulma tarihini içerir.
- **post**: Yoruma ait blog yazısını temsil eder.
- **user**: Yorumu yapan kullanıcıyı temsil eder.

